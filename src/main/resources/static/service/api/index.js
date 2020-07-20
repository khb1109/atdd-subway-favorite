const METHOD = {
    GET_WITH_AUTH() {
        return {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: localStorage.getItem("jwt") || ""
            }
        };
    },
    PUT(data) {
        return {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: localStorage.getItem("jwt") || ""
            },
            body: JSON.stringify({
                ...data
            })
        };
    },
    DELETE() {
        return {
            method: "DELETE",
            headers: {
                Authorization: localStorage.getItem("jwt") || ""
            },
        };
    },

    POST(data) {
        return {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: localStorage.getItem("jwt") || ""
            },
            body: JSON.stringify({
                ...data
            })
        };
    }
};

const api = (() => {
    const request = (uri, config) => fetch(uri, config);
    const requestWithJsonData = (uri, config) =>
        fetch(uri, config).then(response => {
            if (!response.ok) {
                return;
            }
            return response.json();
        });

    const loginMember = {
        create(newMember) {
            return request(`/api/me`, METHOD.POST(newMember));
        },
        login(loginInfo) {
            return requestWithJsonData(`/api/me/login`, METHOD.POST(loginInfo));
        },
        get() {
            return requestWithJsonData(`/api/me`, METHOD.GET_WITH_AUTH());
        },
        update(updatedInfo) {
            return request(`/api/me`, METHOD.PUT(updatedInfo));
        },
        delete() {
            return request(`/api/me`, METHOD.DELETE());
        }
    };

    const line = {
        getAll() {
            return request(`/api/lines/detail`);
        },
        getAllDetail() {
            return requestWithJsonData(`/api/lines/detail`);
        }
    };

    const path = {
        find(params) {
            return requestWithJsonData(
                `/api/paths?source=${params.source}&target=${params.target}&type=${params.type}`
            );
        }
    };

    const favorite = {
        create(favoritePath) {
            return request(`/api/favorites`, METHOD.POST(favoritePath));
        },
        getAll() {
            return requestWithJsonData(`/api/favorites`, METHOD.GET_WITH_AUTH());
        },
        delete(url = "") {
            return request(`/api/favorites${url}`, METHOD.DELETE());
        }
    };

    return {
        loginMember,
        line,
        path,
        favorite
    };
})();

export default api;
