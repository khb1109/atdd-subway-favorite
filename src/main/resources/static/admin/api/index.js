const METHOD = {
    PUT(data) {
        return {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...data
            })
        }
    },
    DELETE() {
        return {
            method: 'DELETE'
        }
    },
    POST(data) {
        return {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...data
            })
        }
    }
}

const api = (() => {
    const request = (uri, config) => fetch(uri, config)
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => data.json())

    const station = {
        get(id) {
            return requestWithJsonData(`/api/stations/${id}`)
        },
        getAll() {
            return requestWithJsonData(`/api/stations`)
        },
        create(data) {
            return requestWithJsonData(`/api/stations`, METHOD.POST(data))
        },
        update(data, id) {
            return requestWithJsonData(`/api/stations/${id}`, METHOD.PUT(data))
        },
        delete(id) {
            return request(`/api/stations/${id}`, METHOD.DELETE())
        }
    }

    const line = {
        get(id) {
            return requestWithJsonData(`/api/lines/${id}`)
        },
        getAll() {
            return requestWithJsonData(`/api/lines`)
        },
        getAllDetail() {
            return requestWithJsonData(`/api/lines/detail`)
        },
        addLineStation(lineId, lineStationCreateRequestView) {
            return request(`/api/lines/${lineId}/stations`, METHOD.POST(lineStationCreateRequestView))
        },
        create(data) {
            return requestWithJsonData(`/api/lines`, METHOD.POST(data))
        },
        update(id, data) {
            return request(`/api/lines/${id}`, METHOD.PUT(data))
        },
        deleteLineStation(lineId, stationId) {
            return request(`/api/lines/${lineId}/stations/${stationId}`, METHOD.DELETE())
        },
        delete(id) {
            return request(`/api/lines/${id}`, METHOD.DELETE())
        }
    }

    return {
        station,
        line
    }
})()

export default api
