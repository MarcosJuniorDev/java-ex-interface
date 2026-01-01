package services;

import entities.Contract;
import entities.Installment;

import java.time.LocalDate;

public class ContractService
{
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService)
    {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months)
    {
        double valuePerMonth = contract.getTotalValue() / months;
        double parcela = valuePerMonth;
        for(int i = 1; i <= months; i++)
        {
            parcela += onlinePaymentService.interest(parcela, i);
            parcela += onlinePaymentService.paymentFee(parcela);
            LocalDate dateForContract = contract.getDate().plusMonths(i);
            contract.addInstallment(parcela, dateForContract);
            parcela = valuePerMonth;
        }


    }
}
