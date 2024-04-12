package Journal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Json {
    private final Product[] products;
    private final JSONObject file = new JSONObject();

    public Json(Product[] products) {
        this.products = products;
    }

    @SuppressWarnings("all")
    public void creatingJson(String filepath) {
        JSONArray listProducts = new JSONArray();
        for (Product product : products) {
            if (product != null) { // Добавляем проверку на null здесь
                JSONObject productObj = new JSONObject();
                productObj.put("Name", product.getName());
                productObj.put("Manufacturer", product.getManufacturer());
                productObj.put("Characteristics", product.getCharacteristics());
                productObj.put("Unit", product.getUnit());
                productObj.put("Cost", product.getCost());
                productObj.put("Quantity", product.getQuantity());
                productObj.put("StorageLocation", product.getStorageLocation());
                listProducts.add(productObj);
            }
        }
        file.put("Products", listProducts);
        try {
            File saveFile = new File(filepath);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            byte[] buffer = file.toJSONString().getBytes();
            outputStream.write(buffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}