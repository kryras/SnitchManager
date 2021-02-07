import Repository from './repository';

const resource = "/snitch"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getSnitch(snitchId) {
        return Repository.get(`${resource}/${snitchId}`);
    },
    createSnitch(payload) {
        return Repository.post(`${resource}`, payload);
    }
}