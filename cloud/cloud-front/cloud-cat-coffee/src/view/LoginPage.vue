<template>
  <a-row>
    <a-col :span="6"></a-col>
    <a-col :span="12"
           v-if="isLogin">
      <a-form-model ref="login"
                    :model="model"
                    :rules="loginValidatorRules">
        <a-form-model-item label="用户名："
                           prop="userName">
          <a-input v-model="model.userName"></a-input>
        </a-form-model-item>
        <a-form-model-item label="密码："
                           prop="password">
          <a-input type="password"
                   v-model="model.password"></a-input>
        </a-form-model-item>
        <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
          <a-button type="primary"
                    @click.prevent="doLogin">登录</a-button>
          <a-button style="margin-left: 10px"
                    @click="resetFields">重置</a-button>
        </a-form-model-item>
      </a-form-model>
    </a-col>
    <a-col :span="12"
           v-else>
      <a-form-model ref="register"
                    :model="model"
                    :rules="registerValidatorRules">

        <a-form-model-item label="用户名："
                           prop="userName">
          <a-input v-model="model.userName"></a-input>
        </a-form-model-item>

        <a-form-model-item label="密码："
                           prop="password">
          <a-input v-model="model.password"></a-input>
        </a-form-model-item>

        <a-form-model-item label="确认密码："
                           prop="confirmPassword">
          <a-input v-model="model.confirmPassword"></a-input>
        </a-form-model-item>
        <a-form-model-item label="邮箱："
                           prop="mail">
          <a-input v-model="model.mail"></a-input>
        </a-form-model-item>

        <a-form-model-item label="生日："
                           prop="birthday">
          <a-input v-model="model.mail"></a-input>
        </a-form-model-item>

        <a-form-model-item label="生日："
                           prop="phone">
          <a-input v-model="model.phone"></a-input>
        </a-form-model-item>

        <a-form-model-item :wrapper-col="{ span: 14, offset: 4 }">
          <a-button type="primary"
                    @click.prevent="doRegister">注册</a-button>
          <a-button style="margin-left: 10px"
                    @click="resetFields">重置</a-button>
        </a-form-model-item>

      </a-form-model>
    </a-col>
    <a-col :span="6"></a-col>
  </a-row>
</template>

<script>
import Vue from 'vue'
import { doLogin } from '@api/api'
import { ACCESS_TOKEN } from "@api/mutation-types"
export default {
  components: {},
  data () {
    return {
      visable: false,
      model: {},
      loginValidatorRules: {
        userName: [{ required: true, message: "请输入用户名!" }],
        password: [{ required: true, message: "请输入密码!" }],
      },
      registerValidatorRules: {
        userName: [{
          required: true, message: "请输入用户名!", validator: () => {
            async (_rule, value) => {
              if (!value) {
                return Promise.reject('请输入用户名！');
              }
              if (value.len < 5) {
                return Promise.reject('用户名长度过短！');
              }
            }
          }
        }],
        password: [{
          required: true, message: "请输入密码!", validator: () => {
            async (_rule, value) => {
              if (!value) {
                return Promise.reject('请输入密码');
              }
              if (value.len < 12) {
                return Promise.reject('密码长度过短！');
              }
            }
          }
        }],
        confirmPassword: [{
          required: true, message: "请确认密码!", validator: () => {
            async (_rule, value) => {
              if (!value) {
                return Promise.reject('请输入确认密码！');
              }
              if (this.model.password != this.model.confirmPassword) {
                return Promise.reject("确认密码与原密码不匹配！");
              }
            }
          }
        }],
        mail: [{ required: true, message: "请输入邮箱(用来找回密码)" }],
        birthday: [{ required: true, message: "请输入生日信息!" }],
        phone: [{ required: true, len: 11, message: "请输入手机!" }],
      },
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    doLogin () {
      this.$refs.login.validate((valid) => {
        if (valid) {
          let that = this
          let user = that.model
          doLogin(user).then((res) => {
            if (res.success) {
              console.log({ res })
              that.$success('登录成功')
              let data = res.data
              let token = data.token
              Vue.ls.set(ACCESS_TOKEN, token, 7 * 24 * 60 * 60 * 1000)
            } else {
              that.$message.warning('登录失败,' + res.message)
            }

          })
        }
      })
    }
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created () { },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted () { },
  beforeCreate () { }, //生命周期 - 创建之前
  beforeMount () { }, //生命周期 - 挂载之前
  beforeUpdate () { }, //生命周期 - 更新之前
  updated () { }, //生命周期 - 更新之后
  beforeUnmount () { }, //生命周期 - 销毁之前
  unmouted () { }, //生命周期 - 销毁完成
  activated () { }, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
</style>