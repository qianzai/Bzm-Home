<template>
  <el-container class="home-container">
    <!--头部布局-->
    <el-header>
      <div>
        <img src="../assets/logo.png"
             alt
             class="logo_img"
        />
        <span>Bzm管理平台</span>
      </div>
      <el-button type="info"
                 @click="logout">安全退出
      </el-button>
    </el-header>
    <el-container>
      <!--侧边布局-->
      <el-aside>
        <el-menu unique-opened
                 :router="true"
                 :default-active="activePath">
          <!--一级菜单-->
          <el-submenu :index="item.id+''"
                      v-for="item in menuList"
                      :key="item.id">
            <template slot="title">
              <i :class="item.icons"></i>
              <span>{{item.title}}</span>
            </template>
            <!--二级菜单-->
            <el-menu-item :index="it.path+''"
                          v-for="it in item.slist"
                          :key="it.id"
                          @click="saveNavState(it.path+'')">
              <i :class="it.icons"></i>
              <span>{{it.title}}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!--主体布局-->
      <el-main>
        <!--路由占位符-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data () {
    return {
      menuList: [],
      activePath: 'test',//默认路径

    }
  },
  /*初始时调用方法*/
  created () {
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath');// 取出session里的访问路径
  },
  methods: {
    /*获取菜单列表*/
    async getMenuList () {
      const { data: res } = await this.$http.get("menus");
      // console.log(res);
      if (!res.status) return this.$message.error("操作失败！！！");
      this.menuList = res.data;

    },
    // 保存二级菜单的路径
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath);// 存放点击的二级菜单
      this.activePath = activePath;// 给点击的菜单添加高亮
    },
    /*登出方法*/
    logout () {
      // window.sessionStorage.clear();
      this.$router.push("/login");
    },
  }
}
</script>

<style lang="less" scoped>
.el-header {
  background-color: #5a5960;
  display: flex;
  justify-content: space-between; // 左右贴边
  padding-left: 0%; // 左边界
  align-items: center; // 水平
  color: #fff;
  font-size: 20px;

  > div {
    //左侧div加布局
    display: flex;
    align-items: center;

    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  /*background-color: #333744;*/

  .el-menu {
    border-right: none; // 对其右边框
  }
}

.el-main {
  background-color: #eaedf1;
}

.home-container {
  height: 100%;
}

.logo_img {
  width: 20%;
  height: 100%;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer; // 显示鼠标指针为：小手
}
</style>
