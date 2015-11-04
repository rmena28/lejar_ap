package menasoft.lejarapp.dto;

/**
 * Created by Rmena on 10/29/2015.
 */
public class Dashboard {
    public String name;
    public double total_balance_paid;
    public double total_balance_unpaid;
    public double todays_personal_balance_paid;
    public double todays_personal_balance_unpaid;
    public double total_personal_balance_paid;
    public double total_personal_balance_unpaid;

    @Override
    public String toString() {
        return "Dashboard{" +
                "name='" + name + '\'' +
                ", total_balance_paid=" + total_balance_paid +
                ", total_balance_unpaid=" + total_balance_unpaid +
                ", todays_personal_balance_paid=" + todays_personal_balance_paid +
                ", todays_personal_balance_unpaid=" + todays_personal_balance_unpaid +
                ", total_personal_balance_paid=" + total_personal_balance_paid +
                ", total_personal_balance_unpaid=" + total_personal_balance_unpaid +
                '}';
    }
}
