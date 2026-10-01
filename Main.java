import java.util.Scanner;

public class Main {
static Scanner sc = new Scanner(System.in);
static Oficina oficina = new Oficina();

public static void main(String[] args) {
criarDados();
int op;
do {
System.out.println("\n1-Cadastrar ordem 2-Associar mecânico 3-Atribuir ordem 4-Ordens do box");
System.out.println("5-Finalizadas por box 6-Buscar por status 7-Detalhes da ordem 8-Finalizar ordem 0-Sair");
op = lerInt("Opção: ");
switch (op) {
case 1: cadastrarOrdem(); break;
case 2: associarMecanico(); break;
case 3: atribuirOrdem(); break;
case 4: ordensDoBox(); break;
case 5: finalizadasPorBox(); break;
case 6: buscarPorStatus(); break;
case 7: detalhesOrdem(); break;
case 8: finalizarOrdem(); break;
case 0: System.out.println("Saindo..."); break;
default: System.out.println("Opção inválida.");
}
} while (op != 0);
}

static void criarDados() {
oficina.addMecanico(new Mecanico("Carlos", "111", "Motor", "3198881111"));
oficina.addMecanico(new Mecanico("Ana", "222", "Freios", "3198882222"));
oficina.addMecanico(new Mecanico("João", "333", "Elétrica", "3198883333"));
oficina.addBox(new Box(1, "Motor", 2, "Galpão A"));
oficina.addBox(new Box(2, "Freios", 3, "Galpão A"));
oficina.addBox(new Box(3, "Elétrica", 2, "Galpão B"));
}

static String ler(String msg) {
System.out.print(msg);
return sc.nextLine();
}

static int lerInt(String msg) {
return Integer.parseInt(ler(msg));
}

static double lerDouble(String msg) {
return Double.parseDouble(ler(msg).replace(",", "."));
}

static void cadastrarOrdem() {
int codigo = lerInt("Código: ");
if (oficina.buscarOrdem(codigo) != null) {
System.out.println("Código já existe.");
} else {
String cliente = ler("Cliente: ");
String modelo = ler("Modelo: ");
String placa = ler("Placa: ");
String data = ler("Data: ");
double valor = lerDouble("Valor estimado: ");
Servico s = new Servico(ler("Serviço: "), lerInt("Tempo (min): "), lerDouble("Valor do serviço: "), ler("Categoria: "));
oficina.addOrdem(new OrdemServico(codigo, cliente, modelo, placa, data, valor, s));
System.out.println("Ordem cadastrada como ABERTA.");
}
}

static void associarMecanico() {
Mecanico m = oficina.buscarMecanico(ler("CPF do mecânico: "));
Box b = oficina.buscarBox(lerInt("Número do box: "));
if (m == null || b == null) {
System.out.println("Mecânico ou box não encontrado.");
} else if (oficina.associarMecanico(m, b)) {
System.out.println("Associado.");
} else {
System.out.println("Esse mecânico já tem box.");
}
}

static void atribuirOrdem() {
OrdemServico o = oficina.buscarOrdem(lerInt("Código da ordem: "));
Box b = oficina.buscarBox(lerInt("Número do box: "));
if (o == null || b == null) {
System.out.println("Ordem ou box não encontrado.");
} else if (oficina.atribuirOrdem(o, b)) {
System.out.println("Ordem em execução no box " + b.getNumero() + ".");
} else {
System.out.println("Não foi possível: ordem não está aberta, tipo diferente ou box cheio.");
}
}

static void ordensDoBox() {
Box b = oficina.buscarBox(lerInt("Número do box: "));
if (b == null) {
System.out.println("Box não encontrado.");
} else {
for (OrdemServico o : b.getOrdens()) {
System.out.println(o);
}
System.out.println("Total: " + b.getOrdens().size());
}
}

static void finalizadasPorBox() {
for (Box b : oficina.getBoxes()) {
System.out.println("Box " + b.getNumero() + ": " + oficina.contarFinalizadas(b) + " finalizada(s)");
}
}

static void buscarPorStatus() {
int s = lerInt("1-Aberta 2-Em execução 3-Finalizada: ");
if (s >= 1 && s <= 3) {
for (OrdemServico o : oficina.filtrarPorStatus(StatusOrdem.values()[s - 1])) {
System.out.println(o);
}
} else {
System.out.println("Status inválido.");
}
}

static void detalhesOrdem() {
OrdemServico o = oficina.buscarOrdem(lerInt("Código: "));
if (o == null) {
System.out.println("Ordem não encontrada.");
} else {
System.out.println(o);
}
}

static void finalizarOrdem() {
OrdemServico o = oficina.buscarOrdem(lerInt("Código: "));
if (o == null) {
System.out.println("Ordem não encontrada.");
} else if (oficina.finalizarOrdem(o)) {
System.out.println("Ordem finalizada.");
} else {
System.out.println("Só dá pra finalizar ordem em execução.");
}
}
}