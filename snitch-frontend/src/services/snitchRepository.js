import Repository from './repository';

const resource = "/snitch"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getById(snitchId) {
        return Repository.get(`${resource}/${snitchId}`);
    },
    create(payload) {
        return Repository.post(`${resource}`, payload);
    },
    getUsers() {
        return Repository.get(`${resource}/user`);
    },
    getTypes() {
        return Repository.get(`${resource}/type`);
    },
    getBonuses() {
        return Repository.get(`${resource}/bonus`);
    }
}