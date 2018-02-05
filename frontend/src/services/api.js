import axios from 'axios'
import config from '../config'

var api = axios.create({
  baseURL: config.url,
  headers: {
    
	}
});
export default api
