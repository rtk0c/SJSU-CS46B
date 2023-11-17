package stacklab;

import java.util.*;


public class UndoRedoPainter extends Painter {
    public UndoRedoPainter() {
        setUndoButtonEnabled(false);
        setRedoButtonEnabled(false);
    }

    // Called when the user pushes the Undo button.
    @Override
    void undo() {
        var src = getHistory();
        var dst = getUndoHistory();
        assert !src.isEmpty();
//        if (src.isEmpty())
//            return;

        dst.add(src.pop());
        // NOTE: setUndoButtonEnabled(true) is called in Painter$Canvas.mouseClicked
        //       no need to worry about it here
        if (src.isEmpty()) {
            setUndoButtonEnabled(false);
        }
        setRedoButtonEnabled(true);
        repaint();
    }

    // Called when the user pushes the Redo button.
    @Override
    void redo() {
        var src = getUndoHistory();
        var dst = getHistory();
        assert !src.isEmpty();
//        if (src.isEmpty())
//            return;

        dst.add(src.pop());
        if (src.isEmpty()) {
            setRedoButtonEnabled(false);
        }
        setUndoButtonEnabled(true);
        repaint();
    }


    public static void main(String[] args) {
        new UndoRedoPainter().setVisible(true);
    }
}
