package com.aplicaoestagio.aplicacaoestagio;

import com.aplicaoestagio.aplicacaoestagio.services.AlunoService;
import com.aplicaoestagio.aplicacaoestagio.services.EmpresaService;
import com.aplicaoestagio.aplicacaoestagio.services.OrientadorService;
import com.aplicaoestagio.aplicacaoestagio.services.EstagioService;
import com.aplicaoestagio.aplicacaoestagio.entidades.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class EstagioCLI {

    private EntityManager entityManager;
    private AlunoService alunoService;
    private EmpresaService empresaService;
    private OrientadorService orientadorService;
    private EstagioService estagioService;

    public EstagioCLI(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.alunoService = new AlunoService(entityManager);
        this.orientadorService = new OrientadorService(entityManager);
        this.estagioService = new EstagioService(entityManager);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Escolha uma ação:");
            System.out.println("1 - Inserir Aluno");
            System.out.println("2 - Atualizar Aluno");
            System.out.println("3 - Remover Aluno");
            System.out.println("4 - Listar Alunos");
             System.out.println("0 - Sair");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // Inserir Aluno
                    Aluno novoAluno = criarAlunoViaInputDoUsuario();
                    alunoService.inserirAluno(novoAluno);
                    break;
                case 2:
                    // Atualizar Aluno
                    Aluno alunoParaAtualizar = atualizarAlunoViaInputDoUsuario();
                    alunoService.atualizarAluno(alunoParaAtualizar);
                    break;
                case 3:
                    // Remover Aluno
                    removerAluno();
                    break;
                case 4:
                    // Listar Alunos
                    listarAlunos();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma ação válida.");
            }
        }
    }

    private Aluno criarAlunoViaInputDoUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.println("Informe a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        System.out.println("Informe o estagio do aluno: ");
        String estagios = scanner.nextLine();
        System.out.println("Informe a empresa do aluno: ");
        String empresa = scanner.nextLine();


        return new Aluno(nome, matricula, estagios, empresa);
    }
    private Aluno atualizarAlunoViaInputDoUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a matrícula do aluno que você deseja atualizar: ");
        String matricula = scanner.nextLine();

        // Verifique se o aluno existe
        Aluno aluno = alunoService.buscarAlunoPorMatricula(matricula);
        if (aluno == null) {
             System.out.println("Aluno não encontrado com a matrícula informada.");
        }

         System.out.println("Informe os novos dados do aluno:");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        aluno.setNome(nome);
        System.out.println("estagio: ");
        String estagios = scanner.nextLine();
        System.out.println("empresa: ");
        String empresa = scanner.nextLine();
        alunoService.atualizarAluno(aluno);
        System.out.println("Aluno atualizado com sucesso.");
        return new Aluno(nome, matricula, estagios, empresa);
    }

    private void removerAluno() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a matrícula do aluno que você deseja remover: ");
        String matricula = scanner.nextLine();

        // Verifique se o aluno existe
        Aluno aluno = alunoService.buscarAlunoPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("Aluno não encontrado com a matrícula informada.");
            return;
        }

        // Remova o aluno do banco de dados
        alunoService.removerAluno(aluno.getId());
        System.out.println("Aluno removido com sucesso.");
    }


    private void listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            System.out.println("Lista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", Matrícula: "
                        + aluno.getMatricula() + "Empresa: " +aluno.getEmpresa() + "Estagio: " +aluno.getEstagios());
            }
        }
    }



    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aplicacao-estagio");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EstagioCLI appCLI = new EstagioCLI(entityManager);
        appCLI.run();

        entityManager.close();
        entityManagerFactory.close();
    }
}
