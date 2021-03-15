<template>
  <!--根容器-->
  <div class="login_container">
    <!--登录盒子-->
    <div class="login_box"
         v-show=isShow>
      <!--图标-->
      <div class="avatar_box">
        <img src="../../assets/logo.png" />
      </div>

      <!--登录表单-->
      <el-form ref="loginFormRef"
               class="login_form"
               :model="loginForm"
               :rules="loginRules"
               label-width="0px">

        <div>
          <el-form-item prop="username">
            <el-input v-model="loginForm.username"
                      prefix-icon="iconfont icondenglu"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="loginForm.password"
                      prefix-icon="iconfont iconmima"
                      type="password"></el-input>
          </el-form-item>
        </div>

        <el-form-item class="btns">
          <el-button type="primary"
                     @click="login">登录</el-button>
          <el-button type="info"
                     @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!--注册盒子-->
    <router-view></router-view>
  </div>
</template>


<script>
export default {
  name: "Login",
  data () {
    return {
      isShow: true,

      /*表单数据*/
      loginForm: {
        username: "admin",
        password: "123456"
      },
      /*表单规则*/
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 8, message: "长度在 3 到 8 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "密码为 6~8 位", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    console.log("created: " + this.isShow);
  },
  methods: {
    /*登陆方法*/
    login () {
      console.log("登录表单");
      console.log(this.loginForm);
      this.$refs.loginFormRef.validate(async valid => {
        // console.log("是否符合验证规则：" + valid);

        /*不符合规则就返回*/
        if (!valid) return;
        const { data: res } = await this.$http.post("login", this.loginForm);
        console.log("data：" + res);
        if (res.status) {
          /*登录成功切换到/home目录*/
          this.$message.success({ message: res.msg, duration: 1000 });
          this.$router.push({ path: "/home" });
        } else {
          this.$message.error({ message: res.msg, duration: 1000 });
        }

      });
    },
    register () {
      // console.log("切换注册");
      this.isShow = false
      this.$router.push({ path: "register" });
    }
  }
}
</script>


<style lang="less" scoped>
// 跟节点样式
.login_container {
  background-color: #abd6b6;
  height: 100%;
}

// 登录框
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px; // 圆角
  position: absolute; // 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%); // 根据自己位置 以自己为长度位移
  // 头像框
  .avatar_box {
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%; // 加圆角
    padding: 3px;
    box-shadow: 0 0 10px #ddd; // 盒子阴影
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #cbbec4;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%; // 加圆角
      background-color: #eee;
    }
  }

  .btns {
    display: flex; // 弹性布局
    justify-content: flex-end; // 尾部对齐
  }

  .login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box; // 设置边框
  }
}
</style>
