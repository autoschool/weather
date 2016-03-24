export function className(name) {
    return target => {
        target.prototype.className = name;
    };
}
export function urlRoot(url) {
    return target => {
        target.prototype.urlRoot = url;
    };
}

export function region(selector) {
    return (target, name, descriptor) => {
        delete descriptor.initializer;
        descriptor.writable = true;
        target.regions = Object.assign({
            [name]: selector
        }, target.regions);
    };
}