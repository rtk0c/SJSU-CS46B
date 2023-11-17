package tiktokers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * An implementation of the AddressBook interface that uses an array list to
 * store the data.
 */
public class ArrayListMyTikTokManager implements MyTikTokManager {

    private ArrayList<Item> items = new ArrayList<Item>();
    private String source;
    private boolean modified;

    public void load(String sourceName) {
        source = sourceName;
        try {
            Scanner in = new Scanner(new File(source));
            items = new ArrayList<Item>();
            while (in.hasNextLine()) {
                items.add(new Item(in.nextLine(), in.nextLine(), in.nextLine()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            source = null;
            items = new ArrayList<Item>();
        }
    }

    public String get(String username, String key) {
        for (Item it : items) {
            if (username.equals(it.getUserName()) && key.equals(it.getKey())) {
                return it.getValue();
            }
        }
        return null;
    }

    public String put(String username, String key, String value) {
        modified = true;
        for (Item it : items) {
            if (username.equals(it.getUserName()) && key.equals(it.getKey())) {
                String oldValue = it.getValue();
                it.setValue(value);
                return oldValue;
            }
        }
        items.add(new Item(username, key, value));
        return null;
    }

    public void save() {
        if (!modified) {
            return;
        }
        try {
            PrintWriter out = new PrintWriter(source);
            for (Item it : items) {
                out.println(it.getUserName());
                out.println(it.getKey());
                out.println(it.getValue());
            }
            out.close();
            modified = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //======== Pseudocode, the implementation plan =========//
    // function remove(username, key)
    //     for i in (0..items.length)
    //         let item = items[i]
    //         if item.username == username && item.key == key
    //             items.remove(i)
    //             mark this as modified
    //             return item.value
    //     return null

    @Override
    public String remove(String username, String key) {
        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            if (item.getUserName().equals(username) && item.getKey().equals(key)) {
//                items.remove(i);
                removeBySwapAndPop(items, i);
                modified = true;
                // NB: this is fine, because `item` the object is not deleted when we remove it from the list
                return item.getValue();
            }
        }
        throw new NoSuchElementException("An entry with username '" + username + "' and key '" + key + "' does not exist.");
//        return null;
    }

    private static <T> void removeBySwapAndPop(ArrayList<T> list, int elmIndex) {
        if (list.isEmpty())
            return;
        int lastIndex = list.size() - 1;
        list.set(elmIndex, list.get(lastIndex));
        list.remove(lastIndex);
    }
}
