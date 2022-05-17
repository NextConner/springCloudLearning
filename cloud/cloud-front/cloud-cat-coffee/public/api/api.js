/* eslint-disable no-unused-vars */
import { getAction, deleteAction, putAction, postAction, httpAction } from '@/api/manage'

const doLogin = (params)=>postAction("/users/login" , params);
