package br.ufc.dc.luthieria.instrumentos;



public class InstrumentoCordas extends InstrumentoAbstrato {

    private int numeroCordas;
    private String materialCorda;

    public InstrumentoCordas(String nome, String marca, String codigo, EstadoInstrumento estado, int numeroCordas, String materialCorda) {
        super(nome, marca, codigo, estado);
        this.numeroCordas = numeroCordas;
        this.materialCorda = materialCorda;
    }

    public int getNumeroCordas() {
        return numeroCordas;
    }

    public void setNumeroCordas(int numeroCordas) {
        this.numeroCordas = numeroCordas;
    }

    public String getMaterialCorda() {
        return materialCorda;
    }

    public void setMaterialCorda(String materialCorda) {
        this.materialCorda = materialCorda;
    }

    @Override
    public String toString() {
        return super.toString() + ", numero de cordas=" + numeroCordas + ", material das cordas='" + materialCorda + '\'';
    }
}
