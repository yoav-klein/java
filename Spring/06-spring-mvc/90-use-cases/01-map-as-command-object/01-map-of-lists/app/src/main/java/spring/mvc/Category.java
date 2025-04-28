package spring.mvc;

public enum Category {
    OTHER("Other"),
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    DAIRY("Dairy"),
    PROTEIN("Protein"),
    BEVERAGES("Beverages"),
    CANDY("Candy"),
    DRY_GOODS("Dry Goods"),
    CANNED_GOODS("Canned Goods"),
    FROZEN_FOOD("Frozen Food"),
    SPICES("Spices"),
    HYGIENE("Hygiene"),
    DISPOSABLE("Disposable"),
    COFFEE("Coffee and Tea"),
    ALCOHOL("Alcohol"),
    BREAD("Bread"),
    CLEANING_SUPPLIES("Cleaning Supplies");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    
}
