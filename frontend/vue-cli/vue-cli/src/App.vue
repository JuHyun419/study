<template>
    <div id="app">
        <TodoHeader123></TodoHeader123>
        <TodoInput></TodoInput>
        <TodoList></TodoList>
        <TodoFooter></TodoFooter>
    </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {
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
