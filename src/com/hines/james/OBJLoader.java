package com.hines.james;

import com.hines.james.math.Vector3f;

import java.io.*;

public class OBJLoader {
    public static Model loadModel(File file) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Model model = new Model();
        String line;

        while((line = reader.readLine()) != null) {
            if(!line.trim().isEmpty()) {
                if (line.startsWith("v ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);

                    model.vertices.add(new Vector3f(x, y, z));
                } else if (line.startsWith("vn ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);

                    model.normals.add(new Vector3f(x, y, z));
                } else if (line.startsWith("f ")) {
                    Vector3f vertexIndices = new Vector3f(
                            Float.valueOf(line.split(" ")[1]),
                            Float.valueOf(line.split(" ")[2]),
                            Float.valueOf(line.split(" ")[3])
                    );

                    model.faces.add(new Face(vertexIndices));
                }
            }
        }

        return model;
    }
}
