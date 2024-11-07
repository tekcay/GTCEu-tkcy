package gregtech.api.unification.material.properties;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record PhysicalProperties(int thermalConductivity, int bp, int mp, int flammeTemperature)
        implements IMaterialProperty {

    public static class Builder {

        private int thermalConductivity = 0;
        private int bp = 0;
        private int mp = 0;
        private int flammeTemperature = 0;

        public Builder thermalConductivity(int thermalConductivity) {
            this.thermalConductivity = thermalConductivity;
            return this;
        }

        public Builder bp(int boilingPoint) {
            this.bp = boilingPoint;
            return this;
        }

        public Builder mp(int meltingPoint) {
            this.mp = meltingPoint;
            return this;
        }

        public Builder flammeTemperature(int flammeTemperature) {
            this.flammeTemperature = flammeTemperature;
            return this;
        }

        public PhysicalProperties build() {
            return new PhysicalProperties(bp, mp, flammeTemperature, thermalConductivity);
        }
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.PHYSICAL_PROPERTIES, true);
    }
}
