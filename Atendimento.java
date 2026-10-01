
public class OrdemServico {
private int codigo;
private String nomeCliente;
private String modeloVeiculo;
private String placa;
private String data;
private double valorEstimado;
private StatusOrdem status;
private Servico servico;
private Box box;

public OrdemServico(int codigo, String nomeCliente, String modeloVeiculo, String placa, String data, double valorEstimado, Servico servico) {
this.codigo = codigo;
this.nomeCliente = nomeCliente;
this.modeloVeiculo = modeloVeiculo;
this.placa = placa;
this.data = data;
this.valorEstimado = valorEstimado;
this.servico = servico;
this.status = StatusOrdem.ABERTA;
}

public boolean iniciar(Box box) {
if (status == StatusOrdem.ABERTA) {
this.box = box;
status = StatusOrdem.EM_EXECUCAO;
return true;
} else {
return false;
}
}

public boolean finalizar() {
if (status == StatusOrdem.EM_EXECUCAO) {
status = StatusOrdem.FINALIZADA;
return true;
} else {
return false;
}
}

public int getCodigo() {
return codigo;
}

public StatusOrdem getStatus() {
return status;
}

public Servico getServico() {
return servico;
}

public Box getBox() {
return box;
}


public String toString() {
String txt = "OS " + codigo + " [" + status + "] " + nomeCliente + " | " + modeloVeiculo + " " + placa
+ " | " + data + " | R$" + valorEstimado + "\n Serviço: " + servico;
if (box == null) {
txt += "\n Box: nenhum";
} else if (box.getMecanico() == null) {
txt += "\n Box " + box.getNumero() + " | Mecânico: nenhum";
} else {
txt += "\n Box " + box.getNumero() + " | Mecânico: " + box.getMecanico().getNome();
}
return txt;
}
}
