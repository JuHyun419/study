package templatemethod;

public class Template {
    public static void main(String[] args) {
        VacationPolicyTemplate usVacationPolicy = new USVacationPolicy();
        VacationPolicyTemplate krVacationPolicy = new KRVacationPolicy();

        // 미국: A, B-1, C
        usVacationPolicy.accrueVacation();

        // 한국: A, B-2, C
        krVacationPolicy.accrueVacation();

    }
}
