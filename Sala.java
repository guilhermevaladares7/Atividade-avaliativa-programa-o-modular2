import java.util.ArrayList;

public class Box {
private int numero;
private String tipoServico;
private int capacidadeMax;
private String localizacao;
private Mecanico mecanico;
private ArrayList<OrdemServico> ordens = new ArrayList<>();

public Box(int numero, String tipoServico, int capacidadeMax, String localizacao) {
this.numero = numero;
this.tipoServico = tipoServico;
this.capacidadeMax = capacidadeMax;
this.localizacao = localizacao;
}

public boolean addOrdem(OrdemServico ordem) {
if (ordens.size() >= capacidadeMax || !ordem.getServico().getCategoria().equalsIgnoreCase(tipoServico)) {
return false;
} else {
ordens.add(ordem);
return true;
}
}

public void removeOrdem(OrdemServico ordem) {
ordens.remove(ordem);
}

public ArrayList<OrdemServico> getOrdens() {
return new ArrayList<>(ordens);
}

public int getNumero() {
return numero;
}

public Mecanico getMecanico() {
return mecanico;
}

public void setMecanico(Mecanico mecanico) {
this.mecanico = mecanico;
}

@Override
public String toString() {
return "Box " + numero + " [" + tipoServico + "] " + localizacao + " | " + ordens.size() + "/" + capacidadeMax + " | " + mecanico;
}
}
