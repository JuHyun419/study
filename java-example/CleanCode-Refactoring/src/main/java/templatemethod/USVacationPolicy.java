package templatemethod;

public class USVacationPolicy extends VacationPolicyTemplate {

    @Override
    protected void alterForLegalMinimums() {
        // 미국 최소 법정 일수를 사용
    }
}
