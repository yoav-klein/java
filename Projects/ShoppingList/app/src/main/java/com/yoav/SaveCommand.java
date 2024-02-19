
package com.yoav;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class SaveCommand implements Command {
    private ShoppingList sl;
    private String fileName;

    SaveCommand(ShoppingList sl, String fileName) {
        this.sl = sl;
        this.fileName = fileName;
    }

    public void execute() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            sl.save(oos);
        } catch(IOException e) {
            System.out.println(e);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
    }
}