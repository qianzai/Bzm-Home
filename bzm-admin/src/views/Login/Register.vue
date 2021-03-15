<template>
  <div class="register_box">
    <el-form :model="registerForm"
             :rules="rules"
             ref="registerFormRef"
             label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="用户名"
                    prop="username">
        <el-input v-model="registerForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码"
                    prop="password">
        <el-input v-model="registerForm.password"></el-input>
      </el-form-item>
      <el-form-item label="邮箱"
                    prop="email">
        <el-input v-model="registerForm.email"></el-input>
      </el-form-item>
      <el-form-item label="角色"
                    prop="role">
        <el-radio-group v-model="registerForm.role">
          <el-radio label="管理员"></el-radio>
          <el-radio label="用户"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="submitForm">创建</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      registerForm: {
        username: '',
        password: '',
        email: '',
        role: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入用户密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度大于6个字符', trigger: 'blur' }
        ],
        role: [
          { required: true, message: '请选择用户角色', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    /*提交注册表单*/
    submitForm () {
      console.log(this.registerForm);
      this.$refs.registerFormRef.validate(async valid => {
        // console.log("是否符合验证规则：" + valid);

        if (!valid) return;
        const { data: res } = await this.$http.post("register", this.registerForm);
        console.log("data：" + res);
        if (res.status) {
          /*登录成功切换到/home目录*/
          this.$message.success({ message: res.msg, duration: 1000 });
          this.$router.push({ path: "/login" });
        } else {
          this.$message.error({ message: res.msg, duration: 1000 });
        }

      });
    },
    /*重置表单*/
    resetForm () {
      this.$refs.registerFormRef.resetFields();
    },
  }
}
</script>

<style lang="less" scoped>
.register_box {
  width: 650px;
  height: 400px;
  background-color: #fff;
  border-radius: 3px; // 圆角
  position: absolute; // 绝对定位
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%); // 根据自己位置 以自己为长度位移
}
</style>
