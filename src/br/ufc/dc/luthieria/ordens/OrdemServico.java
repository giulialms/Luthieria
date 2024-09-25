package br.ufc.dc.luthieria.ordens;
import br.ufc.dc.luthieria.clientes.Cliente;
import br.ufc.dc.luthieria.instrumentos.InstrumentoAbstrato;

public class OrdemServico {
	private int id;
    private Cliente cliente;
    private InstrumentoAbstrato instrumento;
    private String descricaoServico;
    private String dataEntrega;
    private String material;

    public OrdemServico(int id, Cliente cliente, InstrumentoAbstrato instrumento, String descricaoServico, String dataEntrega, String material) 
    //public OrdemServico(int id, Cliente cliente, InstrumentoAbstrato instrumento, String descricaoServico) 
    {
        this.id = id;
        this.cliente = cliente;
        this.instrumento = instrumento;
        this.descricaoServico = descricaoServico;
        this.dataEntrega = dataEntrega;
        this.material = material;
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public InstrumentoAbstrato getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(InstrumentoAbstrato instrumento) {
		this.instrumento = instrumento;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	
}
