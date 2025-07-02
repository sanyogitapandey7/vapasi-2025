class Laptop {
    public byte getLogo() {
        return logo;
    }

    public void setLogo(byte logo) {
        this.logo = logo;
    }

    public Processor getProc() {
        return proc;
    }

    public void setProc(Processor proc) {
        this.proc = proc;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }
    @Override
    public String toString() {
        return "Laptop{" +
                "logo=" + logo +
                ", proc=" + proc +
                ", ram=" + ram +
                ", hdd=" + hdd +
                '}';
    }
    byte logo;
    Processor proc;
    RAM ram;
    HDD hdd;
}
class Processor {
    String generation;
    String frequency;

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "generation='" + generation + '\'' +
                ", frequency='" + frequency + '\'' +
                '}';
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
class RAM {
    String Size;

    @Override
    public String toString() {
        return "RAM{" +
                "Size='" + Size + '\'' +
                '}';
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}
class HDD
{
    String size;

    @Override
    public String toString() {
        return "HDD{" +
                "size='" + size + '\'' +
                '}';}
}