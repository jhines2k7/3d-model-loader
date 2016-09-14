package com.hines.james;

import com.hines.james.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public List<Vector3f> vertices = new ArrayList<Vector3f>();
    public List<Vector3f> normals = new ArrayList<Vector3f>();

    public List<Face> faces = new ArrayList<Face>();

    public Model(){}
}
