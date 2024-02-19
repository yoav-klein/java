
package com.yoav;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class LoadCommand implements Command {
    private ShoppingList sl;
    private String fileName;

    LoadCommand(ShoppingList sl, String fileName) {
        this.sl = sl;
        this.fileName = fileName;
    }

    public void execute() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.fileName))) {
            this.sl.load(ois);
        } catch(IOException e) {
            System.out.println(e);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
    }
}