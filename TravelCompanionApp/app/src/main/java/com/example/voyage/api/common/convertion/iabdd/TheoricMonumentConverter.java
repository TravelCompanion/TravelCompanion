package com.example.voyage.api.common.convertion.iabdd;

import com.example.voyage.travelcompanionapp.model.ApliMonument;

import java.util.ArrayList;

import com.example.voyage.api.common.convertion.generic.MonumentConverter;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.parse.StringParser;

public class TheoricMonumentConverter implements MonumentConverter<Monument>{


    @Override
    public Monument fromDatabase(ApliMonument data) {
        return null;
    }

    @Override
    public ApliMonument toDataBase(Monument data) {
        return null;
    }
}
