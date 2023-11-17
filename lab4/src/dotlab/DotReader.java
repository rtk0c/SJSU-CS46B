package dotlab;

import java.io.*;


public class DotReader {
    private BufferedReader br;
    private int currentLine = 0;

    public DotReader(BufferedReader br) {
        this.br = br;
    }

    public Dot readDot() throws IOException, DotException {
        var line = br.readLine();
        // EOF
        if (line == null) {
            return null;
        }

        var components = line.split(",");
        if (components.length != 4) {
            throw new DotException(String.format("Invalid Dot format at line %d: must be comma-separated line with 4 elements", currentLine));
        }

        String name = components[0];
        String x = components[1];
        String y = components[2];
        String radius = components[3];

        currentLine++;
        return new Dot(name, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(radius));
    }
}
