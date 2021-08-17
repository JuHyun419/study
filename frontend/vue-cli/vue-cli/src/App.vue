<template>
    <div id="app">
        <TodoHeader123></TodoHeader123>
        <TodoInput v-on:addTodo="addTodo"></TodoInput>
        <TodoList v-bind:propsdata="todoItems" v-on:removeTodo="removeTodo"></TodoList>
        <TodoFooter v-on:removeAll="clearAll"></TodoFooter>
    </div>
</template>

<script>
// 컴포넌트 파일 내용 불러오기
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {
    data() {
        return {
            todoItems: []
        }
    },
    created() {
        if (localStorage.length > 0) {
            for (let i = 0; i < localStorage.length; i++) {
                // data() 영역
                this.todoItems.push(localStorage.key(i));
            }
        }
    },
    methods: {
        addTodo(todoItem) { // TodoInput
            localStorage.setItem(todoItem, todoItem);
            this.todoItems.push(todoItem);
        },
        clearAll() { // TodoFooter
            if (confirm("전부 삭제하시겠습니까?")) {
                if (this.todoItems.length == 0) {
                    alert('삭제할 데이터가 존재하지 않습니다.');
                    return;
                }
                localStorage.clear();
                this.todoItems = [];
            }
        },
        removeTodo(todoItem, index) {
            localStorage.removeItem(todoItem);
            this.todoItems.splice(index, 1);
        } 
    },
    components: {
        // 컴포넌트 이름 : 컴포넌트 내용
        'TodoHeader123': TodoHeader,
        'TodoInput': TodoInput,
        'TodoList': TodoList,
        'TodoFooter': TodoFooter
    }
}
</script>

<style>
    body {
        text-align: center;
        background-color: #F6F6F8;
    }

    input {
        border-style: groove;
        width: 200px;
    }

    button {
        border-style: groove;
    }

    .shadow {
        box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03);
    }
</style>
