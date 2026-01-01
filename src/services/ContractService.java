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
        for(int i = 1; i <= months; i++)
        {
            double interest = onlinePaymentService.interest(valuePerMonth, i);
            double fee = onlinePaymentService.paymentFee(valuePerMonth + interest);
            LocalDate dateForContract = contract.getDate().plusMonths(i);
            double total = valuePerMonth + interest + fee;
            contract.addInstallment(total, dateForContract);

        }


    }
}
