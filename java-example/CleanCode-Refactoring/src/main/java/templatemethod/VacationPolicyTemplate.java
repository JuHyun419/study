package templatemethod;

abstract public class VacationPolicyTemplate {

    public void accrueVacation() {
        calculateBaseVacationHours();
        alterForLegalMinimums();
        applyToPayRoll();
    }

    /* 지금까지 근무한 시간을 바탕으로 휴가 일수를 계산하는 코드 */
    private void calculateBaseVacationHours() { }

    /* 휴가 일수가 특정 국가의 최소 법정 일수를 만족하는지 확인하는 코드 */
    abstract protected void alterForLegalMinimums();

    /* 휴가 일수를 급여 대장에 적용하는 코드 */
    private void applyToPayRoll() { }
}
