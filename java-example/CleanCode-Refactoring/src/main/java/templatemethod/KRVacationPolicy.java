package templatemethod;

public class KRVacationPolicy extends VacationPolicyTemplate {

    @Override
    protected void alterForLegalMinimums() {
        // 한국 최소 법정 일수를 사용
    }
}
