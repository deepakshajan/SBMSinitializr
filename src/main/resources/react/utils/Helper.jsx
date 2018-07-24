class Helper {

    static modifyState(data, state) {

        let newState = state;
        let dataObj = JSON.parse(data);
        let key = Object.keys(dataObj)[0];
        if(key === "completed") {
            newState.process[key].push(dataObj[key]);
            newState.process.starting.pop(dataObj[key]);
            let progressValue = Helper.calculateProgressValue(newState);
            newState.progressBar.value = progressValue;
        } else if(key === "starting") {
            newState.process[key].push(dataObj[key]);
        } else if(key === "toBeStarted") {
            newState.process[key].push(dataObj[key]);
        }
        return newState;
    }

    static calculateProgressValue(state) {

        let toBeStartedProcessCount = state.process.toBeStarted.length;
        let completedProcessCount = state.process.completed.length;
        let completionPercentage = Math.floor((completedProcessCount/toBeStartedProcessCount)*100);
        return completionPercentage;
    }

}

export default Helper;