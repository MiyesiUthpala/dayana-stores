package com.stores.dayana.controller;


import com.stores.dayana.dto.ExpiredItemsDto;
import com.stores.dayana.dto.InventoryDto;
import com.stores.dayana.dto.LowStockItemDto;
import com.stores.dayana.dto.StockWorthDto;
import com.stores.dayana.service.InventoryService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@AllArgsConstructor
@RestController
@RequestMapping("/admin/inventory")
public class InventoryController {

    private InventoryService inventoryService;
    private final Environment environment;

    /** API TO ADD NEW ITEM **/
    @PostMapping("/add-item")
    public ResponseEntity<?> addNewItem(@RequestBody InventoryDto inventoryDto)
    {
        InventoryDto savedItem =inventoryService.add_new_item(inventoryDto);
        return ResponseEntity.ok(savedItem);

    }

    /** API TO UPLOAD THE PRODUCT IMAGE TO THE /PRODUCT-IMAGES FOLDER AND RETURN THE SAVED PATH TO FRONTEND WHICH WILL LATER
     * BE DENT TO BE STORED FROM THE FRONTEND **/
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // Define folder where images will be stored
        String uploadDir = "product_images/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {  //check if folder already there if not make a new folder
            if(directory.mkdirs())
            {
                System.out.println("successfully made new product_images folder");
            }
        }

        // Get original filename and save file
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + filename);
        Files.write(filePath, file.getBytes());

        // Dynamically construct image URL
        String serverPort = environment.getProperty("server.port"); // Get running port
        String imageUrl = "http://localhost:" + serverPort + "/images/" + filename;
        System.out.println(imageUrl);
        return ResponseEntity.ok(imageUrl);
    }

    /** API FOR RETRIEVING ALL ITEMS **/
    @GetMapping("/all-items")
    public ResponseEntity<List<InventoryDto>> getAllOrders()
    {
        List<InventoryDto> allOrders=inventoryService.get_all_items();
        return ResponseEntity.ok(allOrders);
    }

    /** API TO DELETE A GIVEN IMAGE USING THE IMAGE PATH, USED FOR THE REMOVE BUTTON IN ITEM ADDING FORM**/
    @DeleteMapping("/delete-image")
    public ResponseEntity<String> deleteImage(@RequestBody Map<String, String> request) {
        String imagePath = request.get("imagePath"); // Extract imagePath from request body

        if (imagePath == null || imagePath.isEmpty()) {
            return ResponseEntity.badRequest().body("Image path is required");
        }

        try {
            // Extract filename from the path
            Path filePath = Paths.get("product_images/", new File(imagePath).getName());

            if (Files.exists(filePath)) {
                Files.delete(filePath); // Delete the file
                return ResponseEntity.ok("Image deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image");
        }
    }

    /** API TO DELETE A GIVEN ITEM USING THE ID SENT AS A VARIABLE IN THE PATH**/
    @DeleteMapping("/delete-item/{deleteItemId}")
    public ResponseEntity<String> deleteImage(@PathVariable("deleteItemId") int id) {

        // Retrieve the image path from the database
        String imagePath = inventoryService.get_image_path_by_id(id);

        if (imagePath != null) {
            try {
                String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
                Path filePath = Paths.get("product_images/",fileName);
                Files.deleteIfExists(filePath); // Delete the image file
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to delete image for item ID: " + id);
            }
        }
        inventoryService.delete_item(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item with id:"+id+" deleted successfully");

    }

    /** API FOR EDITING/UPDATING AN ITEM INFO **/
    @PutMapping("/edit-item")
    public ResponseEntity<?> updateItem(@RequestBody InventoryDto updatedItem){

        InventoryDto editedItem=inventoryService.update_item(updatedItem);
        return ResponseEntity.ok(editedItem);
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics(){
        return ResponseEntity.ok(inventoryService.get_statistics());

    }

    @GetMapping("/low-stock")
    public List<LowStockItemDto> getLowStockItems() {
        return inventoryService.get_low_stock_items();
    }

    @GetMapping("/expired")
    public ResponseEntity<List<ExpiredItemsDto>> getExpiredItems() {
        List<ExpiredItemsDto> expiredItems = inventoryService.get_expired_items();
        return ResponseEntity.ok(expiredItems);
    }

    @GetMapping("/stock-worth")
    public List<StockWorthDto> getStockWorth() {
        return inventoryService.get_stock_worth();
    }
}
