public class Mecanico {
private String nome;
private String cpf;
private String especialidade;
private String telefone;

public Mecanico(String nome, String cpf, String especialidade, String telefone) {
this.nome = nome;
this.cpf = cpf;
this.especialidade = especialidade;
this.telefone = telefone;
}

public String getNome() {
return nome;
}

public String getCpf() {
return cpf;
}

@Override
public String toString() {
return nome + " | CPF " + cpf + " | " + especialidade + " | " + telefone;
}
}