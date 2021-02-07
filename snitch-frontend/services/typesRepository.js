import Repository from './repository';

const resource = "/type"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getById(typeId) {
        return Repository.get(`${resource}/${typeId}`);
    },
    create(payload) {
        return Repository.post(`${resource}`, payload);
    }
}