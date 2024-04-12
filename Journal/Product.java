package Journal;

public class Product {
    private String name;
    private String manufacturer;
    private String characteristics;
    private String unit;
    private int cost;
    private int quantity;
    private String storageLocation;

    // Конструктор
    public Product(String name, String manufacturer, String characteristics, String unit, int cost, int quantity, String storageLocation) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.characteristics = characteristics;
        this.unit = unit;
        this.cost = cost;
        this.quantity = quantity;
        this.storageLocation = storageLocation;
    }

    // Методы доступа к атрибутам
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    // Переопределение метода toString() для удобства отладки и просмотра информации о продукте
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", storageLocation='" + storageLocation + '\'' +
                '}';
    }
}
