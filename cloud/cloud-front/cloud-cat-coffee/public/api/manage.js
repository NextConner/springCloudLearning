import { axios } from './request'
import Vue from 'vue'
import {ACCESS_TOKEN,CLIENT_ID,CLIENT_SECRET,SCOPE,REDIRECT_URI,RESPONSE_TYPE} from './consts'

const api = {
  user: '/mock/api/user',
  role: '/mock/api/role',
  service: '/mock/api/service',
  permission: '/mock/api/permission',
  permissionNoPager: '/mock/api/permission/no-pager',
	config:'',
	auth:'/oauth/auth',
	token:'/oauth/token',
	refreshToken:'/oauth/refresh-token',
}
const authHeader = ()=>{
	//构造请求之前，尝试获取已授权的token
	let token = Vue.ls.get(ACCESS_TOKEN)
	if(token){
		return {
			ACCESS_TOKEN:token
		}
	}
}


const AUTH_URI = process.env.development.VUE_APP_API_AUTH_URL
const TOKEN_URI = process.env.development.VUE_APP_API_AUTH_URL

export default api

const clietnId = process.env.VUE_APP_API_CLIENT_ID
const clietnSecret = process.env.VUE_APP_API_CLIENT_SECRET
const scope = process.env.VUE_APP_API_SCOPE
const responseType = process.env.VUE_APP_API_RESPONSE_TYPE
const redirectUrl = process.env.VUE_APP_API_REDIRECT_URL
const data = {
		CLIENT_ID: clietnId,
		CLIENT_SECRET: clietnSecret,
		SCOPE: scope,
		RESPONSE_TYPE : responseType,
		REDIRECT_URI: redirectUrl
	}

// 授权方法，获取授权码
export async function auth() { 
	return getAction(AUTH_URI,data)
}

 export async function token(tokenData){
	return postAction(TOKEN_URI,tokenData)
 }

export async function login(userModel){
	let userName = userModel.userName
	let password = userModel.password
}

//post
export function postAction(url, parameter,header) {
	
	for(let key in header){
		authHeader[key] = header.key
	}

  return axios({
    url: url,
    method: 'post',
    data: parameter,
    headers: authHeader
  })
}

//post method= {post | put}
export function httpAction(url, parameter, method,header) {
  return axios({
    url: url,
    method: method,
    data: parameter,
    headers: header
  })
}

//put
export function putAction(url, parameter,header) {
  return axios({
    url: url,
    method: 'put',
    data: parameter,
		headers:header
  })
}

//get
export function getAction(url, parameter,header) {

  return axios({
    url: url,
    method: 'get',
    params: parameter,
    headers: header
  })
}

//deleteAction
export function deleteAction(url, parameter,header) {
  return axios({
    url: url,
    method: 'delete',
    params: parameter,
		headers: header
  })
}