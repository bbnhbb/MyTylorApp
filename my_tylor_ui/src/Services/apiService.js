import axios from 'axios';
import { serverUrl } from '../Constants/ApiConstants';

export async function getApiService(url) {
    let response = await axios.get(url);
    return response; 
}

export async function  postApiService(url, body) {
    try {
        let response = await axios.post(`${serverUrl}${url}`, body);
        return response; 
    } catch(e) {
        return e['response']
    }

}