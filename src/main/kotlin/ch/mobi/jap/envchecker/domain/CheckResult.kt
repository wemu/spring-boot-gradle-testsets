package ch.mobi.jap.envchecker.domain

import ch.mobi.jap.envchecker.domain.model.Deployment

data class CheckResult(val from: Deployment, val to: Deployment)