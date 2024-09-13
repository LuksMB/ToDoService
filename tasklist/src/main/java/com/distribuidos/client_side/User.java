package com.distribuidos.client_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.Task;
import com.distribuidos.models.ViewTaskRequest;

public class User {

	public void printMenu() {
		System.out.println("\nDigite o n# da função que deseja executar: ");
		System.out.println("1 - Adicionar Task");
		System.out.println("2 - Ver Task específica");
		System.out.println("3 - Ver Tasks");
		System.out.println("4 - Remover Task");
		System.out.println("5 - Completar Task");
		System.out.println("0 - Sair\n");
	}

	public static void main(String args[]) throws IOException {

		User user = new User();
		int operacao = -1;
		do {
			user.printMenu();
			try {
				operacao = user.selecionaOperacao();
			} catch (IOException ex) {
				System.out.println("Escolha uma das operações pelo número");
			}
		} while (operacao != 0);
	}

	public int selecionaOperacao() throws IOException {

		int operacao = 0;
		String id;
		String resposta = null; 
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String opt = null;
		Proxy proxy = new Proxy();
		do {
			opt = stdin.readLine();
		} while (opt.equals("\n") || opt.equals("") || opt.isEmpty());
		operacao = Integer.parseInt(opt);

		switch (operacao) {
			case 1:
				System.out.print("Digite o título da task: ");
				String titulo = stdin.readLine();
				System.out.println("Digite a descrição da task: ");
				String descricao = stdin.readLine();
				AddTaskRequest addRequest = new AddTaskRequest(titulo, descricao, false);
				resposta = proxy.addTask(addRequest);
				System.out.println("INFO: " + resposta);
				break;

			case 2:
				System.out.print("Digite o número da task: ");
				id = stdin.readLine();
				ViewTaskRequest viewRequest = new ViewTaskRequest(id);
				Task task1 = proxy.viewTask(viewRequest);
				System.out.println("Task:");
				System.out.println("  Título: " + task1.getTitle());
				System.out.println("  Descrição: " + task1.getDescription());
				System.out.println("  Estado: " + (task1.getState() ? "Completa" : "Pendente"));
				System.out.println("---------------------------");
				break;

			case 3:
				try {
					Task[] tasks = proxy.viewAllTasks();
					if (tasks.length == 0){
						System.out.println("Nenhuma task cadastrada.");
					}
					for (Task task : tasks) {
						System.out.println("Task:");
						System.out.println("  Título: " + task.getTitle());
						System.out.println("  Descrição: " + task.getDescription());
						System.out.println("  Estado: " + (task.getState() ? "Completa" : "Pendente"));
						System.out.println("---------------------------");
					}
				} catch (Exception e) {
					System.out.println("INFO: " + e.getMessage());
				}
				
				break;

			case 4:
				System.out.print("Digite o número da task: ");
				id = stdin.readLine();
				RemoveTaskRequest removeRequest = new RemoveTaskRequest(id);
				resposta = proxy.removeTask(removeRequest);
				System.out.println("INFO: " + resposta);
				break;
			
			case 5:
				System.out.print("Digite o número da task que deseja marcar como completa: ");
				id = stdin.readLine();
				CompleteTaskRequest completeRequest = new CompleteTaskRequest(id);
				resposta = proxy.completeTask(completeRequest);
				System.out.println("INFO: " + resposta);
				break;

			case 0:
				System.exit(0);

			default:
				System.out.println("Operação invalida, tente outra.");
				break;
		}
		return operacao;
	}

	
}
