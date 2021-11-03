const Login = {
    namespaced: true,
    state: {
    },
    getters: {
    },
    actions: {
        sign({state}, name) {
            console.log(state, name, '사인사인')
        }
    }
  };
   
  export default Login;