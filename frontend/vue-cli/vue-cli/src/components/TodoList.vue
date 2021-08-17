<template>
    <section>
        <ul>
            <li v-for="(todoItem, index) in propsdata" class="shadow" v-bind:key="todoItem">
                <i class="checkBtn fas fa-check" aria-hidden="true"></i>
                {{ todoItem }}
                <span class="removeBtn" type="button" v-on:click="removeTodo(todoItem, index)">
                    <i class="far fa-trash-alt" aria-hidden="true"></i>
                </span>
            </li>
        </ul>
    </section>
</template>

<script>
export default {
    props: ['propsdata'],

    /* data(), create() => App.vue로 이동(전부 관리) */
    // data() {
    //     return {
    //         todoItems: []
    //     }
    // },
    // created() {
    //     if (localStorage.length > 0) {
    //         for (let i = 0; i < localStorage.length; i++) {
    //             // data() 영역
    //             this.todoItems.push(localStorage.key(i));
    //         }
    //     }
    // },
    methods: {
        removeTodo(todoItem, index) {
            // localStorage.removeItem(todoItem);
            // // splice: 자바스크립트 내장 API, index ~ 1만큼의 인덱스 삭제
            // this.todoItems.splice(index, 1);
            // console.log(todoItem + " " + index);

            // 상위 컴포넌트에게 이벤트 전달
            this.$emit('removeTodo', todoItem, index);
        }
    }
}
</script>

<style scoped>
    ul {
        list-style-type: none; /* 목록 아이템 스타일 지정(앞에 ' 없애기) */
        padding-left: 0px;
        margin-top: 0;
        text-align: left;
    }

    li {
        display: flex; /* 비율 기준의 레리아웃 방식 */
        min-height: 50px;
        height: 50px;
        line-height: 50px;
        margin: 0.5rem 0;
        padding: 0 0.9rem;
        background: white;
        border-radius: 5px;
    }

    .checkBtn {
        line-height: 45px;
        color: #62acde;
        margin-right: 5px;
    }

    .removeBtn {
        margin-left: auto;
        color: #de4343;
    }
</style>
