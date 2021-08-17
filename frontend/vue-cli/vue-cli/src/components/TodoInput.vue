<template>
    <div class="inputBox shadow">
        <input type="text" v-model="newTodoItem" placeholder="Write what you have to do" v-on:keyup.enter="addTodo">
        <span class="addContainer" v-on:click="addTodo">
            <i class="addBtn fas fa-plus" aria-hidden="true"></i>
        </span>
    </div>
</template>

<script>
export default {
    data() {
        return {
            newTodoItem: ''
        }
    },
    methods: {
        addTodo() {
            if (this.newTodoItem == "") {
                alert("값을 입력해주세요!!");
            }
            // localStorage.setItem(this.newTodoItem, this.newTodoItem);
            
            let todoItem = this.newTodoItem && this.newTodoItem.trim();
            // 컴포넌트 간 이벤트 전달 => '하위 컴포넌트에서 발생시킨 이벤트를 상위 컴포넌트에 전달하여 메소드 동작'
            this.$emit('addTodo', todoItem); // Event
            this.clearInput();
        },
        clearInput() {
           this.newTodoItem = "";
        },
    }
}
</script>

<style scoped>
    input:focus {
        outline: none;
    }

    .inputBox {
        background: white;
        height: 50px;
        line-height: 50px;
        border-radius: 5px;
    }

    .inputBox input {
        border-style: none;
        font-size: 0.9rem;
    }

    .addContainer {
        float: right;
        background: linear-gradient(to right, #6478FB, #8763FB);
        display: block;
        width: 3rem;
        border-radius: 0 5px 5px 0;
    }

    .addBtn {
        color: white;
        vertical-align: middle;
    }
</style>
