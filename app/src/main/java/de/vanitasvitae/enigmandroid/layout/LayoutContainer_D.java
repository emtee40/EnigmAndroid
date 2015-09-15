package de.vanitasvitae.enigmandroid.layout;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import de.vanitasvitae.enigmandroid.R;
import de.vanitasvitae.enigmandroid.enigma.EnigmaStateBundle;
import de.vanitasvitae.enigmandroid.enigma.Enigma_D;

/**
 * Concrete LayoutContainer for the M3 layout.
 * This class contains the layout and controls the layout elements such as spinners and stuff
 * Copyright (C) 2015  Paul Schaub

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along
 with this program; if not, write to the Free Software Foundation, Inc.,
 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * @author vanitasvitae
 */
public class LayoutContainer_D extends LayoutContainer
{
    private Enigma_D enigma;

    protected Spinner rotor1PositionView;
    protected Spinner rotor2PositionView;
    protected Spinner rotor3PositionView;
    protected Spinner reflectorPositionView;

    public LayoutContainer_D()
    {
        super();
        main.setTitle("D - EnigmAndroid");
        this.resetLayout();
    }

    @Override
    protected void initializeLayout()
    {
        this.rotor1PositionView = (Spinner) main.findViewById(R.id.rotor1position);
        this.rotor2PositionView = (Spinner) main.findViewById(R.id.rotor2position);
        this.rotor3PositionView = (Spinner) main.findViewById(R.id.rotor3position);
        this.reflectorPositionView = (Spinner) main.findViewById(R.id.reflector_position);
        Button reflectorWiring = (Button) main.findViewById(R.id.button_reflector);
        reflectorWiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PluggableDialogBuilder(state).showDialogReflector();
            }
        });

        Character[] rotorPositionArray = new Character[26];
        for(int i=0; i<26; i++) {rotorPositionArray[i] = (char) (65+i); /**Fill with A..Z*/}

        ArrayAdapter<Character> rotor1PositionAdapter = new ArrayAdapter<>(main.getApplicationContext(),
                android.R.layout.simple_spinner_item,rotorPositionArray);
        rotor1PositionAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        rotor1PositionView.setAdapter(rotor1PositionAdapter);
        ArrayAdapter<Character> rotor2PositionAdapter = new ArrayAdapter<>(main.getApplicationContext(),
                android.R.layout.simple_spinner_item,rotorPositionArray);
        rotor2PositionAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        rotor2PositionView.setAdapter(rotor2PositionAdapter);
        ArrayAdapter<Character> rotor3PositionAdapter = new ArrayAdapter<>(main.getApplicationContext(),
                android.R.layout.simple_spinner_item,rotorPositionArray);
        rotor3PositionAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        rotor3PositionView.setAdapter(rotor3PositionAdapter);
        ArrayAdapter<Character> reflectorPositionAdapter = new ArrayAdapter<>(main.getApplicationContext(),
                android.R.layout.simple_spinner_item,rotorPositionArray);
        reflectorPositionAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        reflectorPositionView.setAdapter(reflectorPositionAdapter);
    }

    @Override
    public void resetLayout()
    {
        enigma = new Enigma_D();
        setLayoutState(enigma.getState());
        output.setText("");
        input.setText("");
    }

    @Override
    protected void setLayoutState(EnigmaStateBundle state)
    {
        this.state = state;
        this.rotor1PositionView.setSelection(state.getRotationRotor1());
        this.rotor2PositionView.setSelection(state.getRotationRotor2());
        this.rotor3PositionView.setSelection(state.getRotationRotor3());
        this.reflectorPositionView.setSelection(state.getRotationReflector());
    }

    @Override
    protected void refreshState()
    {
        state.setRotationRotor1(rotor1PositionView.getSelectedItemPosition());
        state.setRotationRotor2(rotor2PositionView.getSelectedItemPosition());
        state.setRotationRotor3(rotor3PositionView.getSelectedItemPosition());
        state.setRotationReflector(reflectorPositionView.getSelectedItemPosition());
    }

    public Enigma_D getEnigma()
    {
        return this.enigma;
    }

    @Override
    public void showRingSettingsDialog()
    {
        new RingSettingsDialogBuilder.RingSettingsDialogBuilderRotRotRotRef().
                createRingSettingsDialog(state);
    }
}