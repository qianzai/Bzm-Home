<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区-->
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <!-- 搜索添加 -->
          <el-input placeholder="请输入搜索内容"
                    v-model="queryInfo.query"
                    clearable
                    @clear="getUserList">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <!--添加用户-->
          <el-button type="primary"
                     @click="addDialogVisible = true">添加用户
          </el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 用户列表 -->
    <el-table :data="userList"
              border
              stripe
              style="width: 100%">
      <!--索引列-->
      <el-table-column type="index"></el-table-column>
      <el-table-column label="姓名"
                       prop="username"></el-table-column>
      <el-table-column label="邮箱"
                       prop="email"></el-table-column>
      <el-table-column label="密码"
                       prop="password"></el-table-column>
      <el-table-column label="角色"
                       prop="role"></el-table-column>
      <el-table-column label="状态"
                       prop="state">
        <!-- scope.row 就是当前行的信息 -->
        <template slot-scope="scope">
          <el-switch v-model="scope.row.state"
                     @change="userStateChanged(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <!--操作列插槽-->
        <template slot-scope="scope">
          <!-- 修改 -->
          <el-button type="primary"
                     icon="el-icon-edit"
                     size="mini"></el-button>
          <!-- 删除 -->
          <el-button type="danger"
                     icon="el-icon-delete"
                     size="mini"  @click="deleteUser(scope.row.id)"></el-button>
          <!-- 权限 -->
          <el-tooltip effect="dark"
                      content="分配权限"
                      placement="top-start"
                      :enterable="false">
            <el-button type="warning"
                       icon="el-icon-setting"
                       size="mini"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页插件 -->
    <div>
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="queryInfo.pageNum"
                     :page-sizes="[1, 2, 5, 100]"
                     :page-size="queryInfo.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total"></el-pagination>
    </div>

    <!--新增用户-->
    <div>
      <el-dialog title="添加用户"
                 :visible.sync="addDialogVisible"
                 @colse="addDialogClosed">
        <!-- 内容主体区域 -->
        <el-form :model="addForm"
                 :rules="addFormRules"
                 ref="addFormRef"
                 label-width="70px">
          <!-- 用户名 -->
          <el-form-item label="用户名"
                        prop="username">
            <el-input v-model="addForm.username"></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item label="密码"
                        prop="password">
            <el-input v-model="addForm.password"></el-input>
          </el-form-item>
          <!-- 邮箱 -->
          <el-form-item label="邮箱"
                        prop="email">
            <el-input v-model="addForm.email"></el-input>
          </el-form-item>
        </el-form>
        <!-- 内容底部区域 -->
        <span slot="footer"
              class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary"
                     @click="addUser">确 定</el-button>
        </span>
      </el-dialog>
    </div>

  </div>
</template>

<script>
export default {
  name: "User",
  data () {
    return {

      // 请求数据
      queryInfo: {
        query: "",  /*查询信息*/
        pageNum: 1, /*当前页*/
        pageSize: 5 /*每页最大数*/
      },
      // 添加用户表单项
      addForm: {
        username: '',
        password: '',
        email: '',
      },

      //用户列表
      userList: [],
      total: 0, // 最大数据记录
      addDialogVisible: false,// 对话框显示

      // 验证规则
      addFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 1, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 1, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
        ],
      },

    }
  },

  /*初始方法*/
  created () {
    this.getUserList();
  },

  methods: {
    /*获取用户列表*/
    async getUserList () {
      // 调用post请求
      const { data: res } = await this.$http.get("user/findAll", {
        params: this.queryInfo
      });
      this.userList = res.data.userList; // 将返回数据赋值
      this.total = res.data.numbers; // 总个数
      // console.log(this.total);
    },
    // 监听pageSize改变的事件
    handleSizeChange (newSize) {
      this.queryInfo.pageSize = newSize;
      this.getUserList(); // 数据发生改变重新申请数据
    },
    // 监听pageNum改变的事件
    handleCurrentChange (newPage) {
      this.queryInfo.pageNum = newPage;
      this.getUserList(); // 数据发生改变重新申请数据
    },
    // 修改用户状态
    async userStateChanged (userinfo) {
      const { data: res } = await this.$http.put(
        `user/userState?id=${userinfo.id}&state=${userinfo.state}`
      );
      if (res.msg != "success") {
        userinfo.state = !userinfo.state;
        return this.$message.error("操作失败！！！");
      }
      this.$message.success("操作成功！！！");
    },
    // 监听添加用户
    addDialogClosed () {
      this.$refs.addFormRef.resetFields();// 重置表单项
    },
    // 添加用户
    addUser (userinfo) {
      this.$refs.addFormRef.validate(async valid => {
        console.log(valid);
        console.log(userinfo);
        if (!valid) return;
        const { data: res } = await this.$http.post("register", this.addForm);
        if (res.status) {
          // userinfo.state = !userinfo.state;
          this.$message.success("操作成功！！！");
        } else {
          this.$message.error("操作失败！！！");
        }

        //隐藏
        this.addDialogVisible = false;
        this.getUserList();
      })
    },
    // 删除按钮
    async deleteUser(id){
      // 弹框
      const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      // 成功删除为confirm 取消为 cancel
      if(confirmResult!='confirm'){
        return this.$message.info("已取消删除");
      }
      const {data:res} = await this.$http.delete("user/deleteUser?id="+id);
      if (res.msg != "success") {
        return this.$message.error("操作失败！！！");
      }
      this.$message.success("操作成功！！！");
      this.getUserList();
    },
  },
}
</script>

<style scoped>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
