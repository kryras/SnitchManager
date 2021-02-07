import Repository from './repository';

const resource = "/type"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getType(typeId) {
        return Repository.get(`${resource}/${typeId}`);
    },
    createType(payload) {
        return Repository.post(`${resource}`, payload);
    }
}