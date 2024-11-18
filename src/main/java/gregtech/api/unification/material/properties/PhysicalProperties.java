package gregtech.api.unification.material.properties;

import com.github.bsideup.jabel.Desugar;
import com.google.common.base.Preconditions;

import gregtech.api.unification.material.Material;

import net.minecraft.client.resources.I18n;

import net.minecraft.util.text.TextFormatting;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Desugar
public record PhysicalProperties(int bp, int mp, int flameTemperature, int thermalConductivity, boolean isPyrophoric, boolean isHygroscopic) implements IMaterialProperty {

    public static List<String> createPhysicalPropertiesTooltip(@NotNull Material material) {
        List<String> tooltips = new ArrayList<>();

        if (material.hasProperty(PropertyKey.PHYSICAL_PROPERTIES)) {
            PhysicalProperties physicalProperties = material.getPhysicalProperties();

            if (physicalProperties.isHygroscopic()) {
                tooltips.add(I18n.format("gregtech.physical_properties.hygroscopic"));
            }
            if (physicalProperties.isPyrophoric()) {
                tooltips.add(I18n.format("gregtech.physical_properties.pyrophoric"));
            }
            if (physicalProperties.mp() > 0) {
                tooltips.add(I18n.format("gregtech.physical_properties.mp", physicalProperties.mp()));
            }
            if (physicalProperties.bp() > 0) {
                tooltips.add(I18n.format("gregtech.physical_properties.bp", physicalProperties.bp()));
            }
            if (physicalProperties.thermalConductivity() > 0) {
                tooltips.add(I18n.format("gregtech.physical_properties.thermal_conductivity", physicalProperties.thermalConductivity()));
            }
        }
        return tooltips;
    }

    public static class Builder {

        private int thermalConductivity = 0;
        private int bp = 0;
        private int mp = 0;
        private int flameTemperature = 0;
        private boolean isPyrophoric = false;
        private boolean isHygroscopic = false;

        public Builder thermalConductivity(int thermalConductivity) {
            this.thermalConductivity = thermalConductivity;
            return this;
        }

        public Builder bp(int boilingPoint) {
            Preconditions.checkArgument(boilingPoint > 0, "Boiling point must be > 0");
            this.bp = boilingPoint;
            return this;
        }

        public Builder mp(int meltingPoint) {
            Preconditions.checkArgument(meltingPoint > 0, "Melting point must be > 0");
            this.mp = meltingPoint;
            return this;
        }

        public Builder flameTemperature(int flameTemperature) {
            Preconditions.checkArgument(flameTemperature > 0, "flameTemperature must be > 0");
            this.flameTemperature = flameTemperature;
            return this;
        }

        public Builder pyrophoric() {
            this.isPyrophoric = true;
            return this;
        }

        public Builder hygroscopic() {
            this.isHygroscopic = true;
            return this;
        }

        public PhysicalProperties build() {
            return new PhysicalProperties(bp, mp, flameTemperature, thermalConductivity, isPyrophoric, isHygroscopic);
        }
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.PHYSICAL_PROPERTIES, true);
    }
}
