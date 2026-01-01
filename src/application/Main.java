package application;

import entities.Contract;
import services.ContractService;
import services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Entre so dados do contrato: ");
        System.out.print("Numero: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), formatter);
        System.out.print("Valor do contrato: ");
        double valueContract = Double.parseDouble(sc.nextLine());
        System.out.print("Entre com o numero de parcelas: ");
        int nInstallsments = Integer.parseInt(sc.nextLine());
        Contract c = new Contract(number, date, valueContract);
        ContractService service = new ContractService(new PaypalService());
        service.processContract(c, nInstallsments);
        c.showInstallment();









        sc.close();
    }
}
